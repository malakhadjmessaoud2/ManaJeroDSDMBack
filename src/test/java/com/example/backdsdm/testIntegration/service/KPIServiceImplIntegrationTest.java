package com.example.backdsdm.testIntegration.service;

import com.example.backdsdm.Repositories.KPIRepository;
import com.example.backdsdm.entities.KPI;
import com.example.backdsdm.services.KPIServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class KPIServiceImplIntegrationTest {

    @Autowired
    private KPIServiceImpl kpiService;

    @Autowired
    private KPIRepository kpiRepository;

    @Test
    public void testGetKPIsByProjectId() {
        // Préparez les données de test
        KPI kpi1 = new KPI();
        kpi1.setProjectId("123");
        kpi1.setName("KPI 1");
        kpi1.setValue("Value 1");

        KPI kpi2 = new KPI();
        kpi2.setProjectId("123");
        kpi2.setName("KPI 2");
        kpi2.setValue("Value 2");

        kpiRepository.save(kpi1);
        kpiRepository.save(kpi2);

        // Appelez la méthode sous test
        List<KPI> retrievedKPIs = kpiService.getKPIsByProjectId("123");

        // Assertions
        Assertions.assertNotNull(retrievedKPIs, "KPIs ne doivent pas être null");
        Assertions.assertEquals("KPI 1", retrievedKPIs.get(0).getName(), "Le nom du premier KPI ne correspond pas");
        Assertions.assertEquals("Value 1", retrievedKPIs.get(0).getValue(), "La valeur du premier KPI ne correspond pas");
        Assertions.assertEquals("KPI 2", retrievedKPIs.get(1).getName(), "Le nom du second KPI ne correspond pas");
        Assertions.assertEquals("Value 2", retrievedKPIs.get(1).getValue(), "La valeur du second KPI ne correspond pas");
    }

    @Test
    public void testAddKPI() {
        // Appelez la méthode sous test
        KPI savedKPI = kpiService.addKPI("123", "New KPI", "New Value");

        // Assertions
        Assertions.assertNotNull(savedKPI, "Le KPI sauvegardé ne doit pas être null");
        Assertions.assertEquals("123", savedKPI.getProjectId(), "L'ID du projet ne correspond pas");
        Assertions.assertEquals("New KPI", savedKPI.getName(), "Le nom ne correspond pas");
        Assertions.assertEquals("New Value", savedKPI.getValue(), "La valeur ne correspond pas");

        // Vérifiez que le KPI a bien été sauvegardé en base de données
        KPI kpiFromDb = kpiRepository.findById(savedKPI.getId()).orElse(null);
        Assertions.assertNotNull(kpiFromDb, "Le KPI doit être présent en base de données");
        Assertions.assertEquals("123", kpiFromDb.getProjectId(), "L'ID du projet en base de données ne correspond pas");
        Assertions.assertEquals("New KPI", kpiFromDb.getName(), "Le nom en base de données ne correspond pas");
        Assertions.assertEquals("New Value", kpiFromDb.getValue(), "La valeur en base de données ne correspond pas");
    }
}
