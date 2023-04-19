package com.mjc.school;


import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;


import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideOutsideOfPackages;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
public class MainTest {

    DescribedPredicate<? super JavaClass> moduleMain = resideInAPackage("com.mjc.school")
            .and(resideOutsideOfPackages("com.mjc.school.controller", "com.mjc.school.service", "com.mjc.school.repository" ));
    DescribedPredicate<? super JavaClass> moduleWeb = resideInAPackage("com.mjc.school.controller");
    DescribedPredicate<? super JavaClass> moduleService = resideInAPackage("com.mjc.school.service");
    DescribedPredicate<? super JavaClass> moduleRepository = resideInAPackage("com.mjc.school.repository");

    @Test
    void projectShouldFollowLayeredArchitecture(){
        var javaClasses = new ClassFileImporter().importPackages("com.mjc.school");
        var layeredArchitecture =
                layeredArchitecture().consideringOnlyDependenciesInLayers()
                        .layer("main")
                        .definedBy(moduleMain)
                        .layer("web")
                        .definedBy(moduleWeb)
                        .layer("service")
                        .definedBy(moduleService)
                        .layer("repository")
                        .definedBy(moduleRepository)
                        .whereLayer("main")
                        .mayNotBeAccessedByAnyLayer()
                        .whereLayer("web")
                        .mayOnlyBeAccessedByLayers("main", "web")
                        .whereLayer("service")
                        .mayOnlyBeAccessedByLayers("web", "service")
                        .whereLayer("repository")
                        .mayOnlyBeAccessedByLayers("repository", "service");

        layeredArchitecture.check(javaClasses);

    }


}
