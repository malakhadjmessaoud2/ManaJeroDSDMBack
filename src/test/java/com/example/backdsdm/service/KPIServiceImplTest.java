package com.example.backdsdm.service;

import com.example.backdsdm.Repositories.KPIRepository;
import com.example.backdsdm.entities.KPI;
import com.example.backdsdm.services.KPIServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KPIServiceImplTest {

    @Mock
    private KPIRepository kpiRepository;

    @InjectMocks
    private KPIServiceImpl kpiService;

    @Test
    public void testGetKPIsByProjectId() {
        // Create a sample list of KPIs
        KPI kpi1 = new KPI();
        kpi1.setProjectId("123");
        kpi1.setName("KPI 1");
        kpi1.setValue("Value 1");

        KPI kpi2 = new KPI();
        kpi2.setProjectId("123");
        kpi2.setName("KPI 2");
        kpi2.setValue("Value 2");

        List<KPI> kpis = Arrays.asList(kpi1, kpi2);

        // Mock the repository behavior
        when(kpiRepository.findByProjectId("123")).thenReturn(kpis);

        // Call the method under test
        List<KPI> retrievedKPIs = kpiService.getKPIsByProjectId("123");

        // Assertions
        Assertions.assertNotNull(retrievedKPIs, "KPIs should not be null");
        Assertions.assertEquals(2, retrievedKPIs.size(), "The size of KPIs list should be 2");
        Assertions.assertEquals("KPI 1", retrievedKPIs.get(0).getName(), "Name of the first KPI mismatch");
        Assertions.assertEquals("Value 1", retrievedKPIs.get(0).getValue(), "Value of the first KPI mismatch");
        Assertions.assertEquals("KPI 2", retrievedKPIs.get(1).getName(), "Name of the second KPI mismatch");
        Assertions.assertEquals("Value 2", retrievedKPIs.get(1).getValue(), "Value of the second KPI mismatch");
    }

    @Test
    public void testAddKPI() {
        // Create a sample KPI
        KPI kpi = new KPI();
        kpi.setProjectId("123");
        kpi.setName("New KPI");
        kpi.setValue("New Value");

        // Mock the repository behavior
        when(kpiRepository.save(any(KPI.class))).thenReturn(kpi);

        // Call the method under test
        KPI savedKPI = kpiService.addKPI("123", "New KPI", "New Value");

        // Assertions
        Assertions.assertNotNull(savedKPI, "Saved KPI should not be null");
        Assertions.assertEquals("123", savedKPI.getProjectId(), "Project ID mismatch");
        Assertions.assertEquals("New KPI", savedKPI.getName(), "Name mismatch");
        Assertions.assertEquals("New Value", savedKPI.getValue(), "Value mismatch");
    }
}
