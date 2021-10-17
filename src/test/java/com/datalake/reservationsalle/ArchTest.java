package com.datalake.reservationsalle;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.datalake.reservationsalle");

        noClasses()
            .that()
                .resideInAnyPackage("com.datalake.reservationsalle.service..")
            .or()
                .resideInAnyPackage("com.datalake.reservationsalle.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.datalake.reservationsalle.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
