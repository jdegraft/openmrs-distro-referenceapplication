package org.openmrs.reference;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.PatientCaptureVitalsPage;
import org.openmrs.reference.page.PatientVisitsDashboardPage;
import org.openmrs.uitestframework.test.TestBase;
import org.openmrs.uitestframework.test.TestData.PatientInfo;


/**
 * Created by tomasz on 25.05.15.
 */
public class CaptureVitalsTest  extends TestBase {


    private HomePage homePage;
    private ClinicianFacingPatientDashboardPage clinicianFacingPatientDashboardPage;
    private PatientCaptureVitalsPage patientCaptureVitalsPage;
    private PatientInfo patient;
    PatientVisitsDashboardPage patientVisitsDashboardPage;

    @Before
    public void setUp() throws InterruptedException {
        homePage = new HomePage(page);
        clinicianFacingPatientDashboardPage = new ClinicianFacingPatientDashboardPage(page);
        patientVisitsDashboardPage = new PatientVisitsDashboardPage(driver);

        patientCaptureVitalsPage = new PatientCaptureVitalsPage(driver);
        assertPage(homePage);
    }

    private void registerAPatient() throws InterruptedException{
        patient = createTestPatient();
        clinicianFacingPatientDashboardPage.go(patient.uuid);
    }

    @After
    public void tearDown() throws Exception {
        deletePatient(patient.uuid);
    }

    @Test
    @Category(org.openmrs.reference.groups.BuildTests.class)
    public void captureVital() throws InterruptedException {
        registerAPatient();
        clinicianFacingPatientDashboardPage.startVisit();
        patientVisitsDashboardPage.goToCaptureVitals();
        patientCaptureVitalsPage.waitForJsVariable("Navigator.isReady");
        patientCaptureVitalsPage.setHeightField("185");
        patientCaptureVitalsPage.setWeightField("78");
        patientCaptureVitalsPage.setTemperatureField("36.6");
        patientCaptureVitalsPage.setPulseField("120");
        patientCaptureVitalsPage.setRespiratoryField("110");
        patientCaptureVitalsPage.setBloodPressureFields("120", "70");
        patientCaptureVitalsPage.setBloodOxygenSaturationField("50");
        patientCaptureVitalsPage.confirm();
        patientCaptureVitalsPage.save();
    }


}
