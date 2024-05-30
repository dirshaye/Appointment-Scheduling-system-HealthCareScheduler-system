
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;   

/**
 * Abstract class representing a person and contact details.
 */
abstract class Person {
    private String name;
    private String contactNumber;
 
    /**
     *  Constructor initializing the person with name and contact number.
     * @param name Name of the person.
     * @param contactNumber Contact number of the person.
     */
    public Person(String name, String contactNumber) {  
        this.name = name;
        this.contactNumber = contactNumber; 
    }

    /**
     * Gets the name of the person. 
     * @return Name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the contact number of the person.
     * @return Contact number of the person.
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Displays basic information of the person.
     */
    public void displayInformation() {
        System.out.println("Name: " + name);
        System.out.println("Contact Number: " + contactNumber);
    }

    /**
     * Displays information with an additional message.
     * @param additionalMessage Additional message to be displayed.
     */
    public void displayInformation(String additionalMessage) {
        System.out.println(additionalMessage);
        displayInformation(); // Reusing existing method
    }

    /**
     * Displays information with age.
     * @param age Age of the person.
     */
    public void displayInformation(int age) {
        System.out.println("Age: " + age);
        displayInformation(); // Reusing existing method
    }

    /**
     * Displays information with a custom label.
     * @param customLabel Label for the information.
     * @param value Value to be displayed next to the label.
     */
    public void displayInformation(String customLabel, String value) {
        System.out.println(customLabel + ": " + value);
    }

    /**
     * Static nested class representing contact information.
     */
    static class ContactInformation {
        private String email;

        /**
         * Constructor initializing with email.
         * @param email Email address.
         */
        public ContactInformation(String email) {
            this.email = email;
        }

        /**
         * Gets the email address of the person.
         * @return Email address.
         */
        public String getEmail() {
            return email;
        }
    }
}

/**
 * Interface for appointment scheduler functionality.
 */
interface AppointmentScheduler {
    /**
     * Schedules an appointment.
     * @param appointment Appointment to be scheduled.
     */
    void scheduleAppointment(Appointment appointment);

    /**
     * Displays all scheduled appointments.
     * @return List of all appointments.
     */
    List<Appointment> viewAppointments();
}

/**
 * Class representing an appointment.
 */
class Appointment {
    private String date;
    private Person doctor;
    private Person patient;

    /**
     * Constructor initializing appointment details.
     * @param date Date of the appointment.
     * @param doctor Doctor for the appointment.
     * @param patient Patient for the appointment.
     */
    public Appointment(String date, Person doctor, Person patient) {
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
    }

    /**
     * Gets the date of the appointment.
     * @return Date of the appointment.
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the doctor for the appointment.
     * @return Doctor object.
     */
    public Person getDoctor() {
        return doctor;
    }

    /**
     * Gets the patient for the appointment.
     * @return Patient object.
     */
    public Person getPatient() {
        return patient;
    }
}

/**
 * Class representing a doctor, derived from the Person class.
 */
class Doctor extends Person {
    private String specialization;

    /**
     * Constructor initializing doctor details.
     * @param name Name of the doctor.
     * @param contactNumber Contact number of the doctor.
     * @param specialization Specialization of the doctor.
     */
    public Doctor(String name, String contactNumber, String specialization) {
        super(name, contactNumber);
        this.specialization = specialization;
    }

    /**
     * Gets the specialization of the doctor.
     * @return Specialization of the doctor.
     */
    public String getSpecialization() {
        return specialization;
    }
}

/**
 * Class representing a patient, derived from the Person class.
 */
class Patient extends Person {
    private String healthCondition;

    /**
     * Constructor initializing patient details.
     * @param name Name of the patient.
     * @param contactNumber Contact number of the patient.
     * @param healthCondition Health condition of the patient.
     */
    public Patient(String name, String contactNumber, String healthCondition) {
        super(name, contactNumber);
        this.healthCondition = healthCondition;
    }

    /**
     * Gets the health condition of the patient.
     * @return Health condition of the patient.
     */
    public String getHealthCondition() {
        return healthCondition;
    }
}

/**
 * Class implementing the AppointmentScheduler interface.
 */
class Scheduler implements AppointmentScheduler {
    private List<Appointment> appointments;

    /**
     * Constructor initializing with an empty list of appointments.
     */
    public Scheduler() {
        this.appointments = new ArrayList<>();
    }

    /**
     * Schedules an appointment if it's valid.
     * @param appointment Appointment to be scheduled.
     */
    @Override
    public void scheduleAppointment(Appointment appointment) {
        try{
            if (isAppointmentValid(appointment)) {
                appointments.add(appointment);
                System.out.println("Appointment successfully scheduled.");
            } else {
                System.out.println("Appointment could not be scheduled. Check for conflicts or invalid entries.");
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Displays all scheduled appointments.
     * @return List of all appointments.
     */
    @Override
    public List<Appointment> viewAppointments() {
        return new ArrayList<>(appointments);
    }

    /**
     * Checks if an appointment is valid.
     * @param appointment Appointment to be checked for validity.
     * @return true if the appointment is valid, false otherwise.
     */
    private boolean isAppointmentValid(Appointment appointment) {
        // Validate appointment here
        if (appointment == null ||
            appointment.getDoctor().getName().trim().isEmpty() ||
            appointment.getPatient().getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment information cannot be empty or invalid. Please provide valid information.");
        }

        // Additional validation logic...

        // If all validation checks pass, the appointment is considered valid
        return true;
    }

    /**
     * Main method to test scheduler functionality.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scheduler scheduler = new Scheduler();
        while (true) {
            // Collecting information for a new appointment
            System.out.print("Doctor's Name: ");
            String doctorName = scanner.nextLine();
            System.out.print("Doctor's Contact Number: ");
            String doctorContactNumber = scanner.nextLine();
            System.out.print("Doctor's Specialization: ");
            String doctorSpecialization = scanner.nextLine();
            Doctor doctor = new Doctor(doctorName, doctorContactNumber, doctorSpecialization);

            System.out.print("Patient's Name: ");
            String patientName = scanner.nextLine();
            System.out.print("Patient's Contact Number: ");
            String patientContactNumber = scanner.nextLine();
            System.out.print("Patient's Health Condition: ");
            String patientHealthCondition = scanner.nextLine();
            Patient patient = new Patient(patientName, patientContactNumber, patientHealthCondition);

            System.out.print("Appointment Date (YYYY-MM-DD): ");
            String appointmentDate = scanner.nextLine();

            // Creating a new appointment
            Appointment newAppointment = new Appointment(appointmentDate, doctor, patient);

            // Scheduling the appointment
            scheduler.scheduleAppointment(newAppointment);

            // Asking the user if they want to schedule another appointment
            System.out.print("Would you like to schedule another appointment? (yes/no): ");
            if (!scanner.nextLine().equalsIgnoreCase("yes")) {
                break;
            }
        }

        // Displaying all appointments
        List<Appointment> allAppointments = scheduler.viewAppointments();
        System.out.println("All Appointments:");
        for (Appointment apt : allAppointments) {
            System.out.println("Date: " + apt.getDate());
            System.out.println("Doctor: " + apt.getDoctor().getName() + " (Specialization: " + ((Doctor) apt.getDoctor()).getSpecialization() + ")");
            System.out.println("Patient: " + apt.getPatient().getName() + " (Health Condition: " + ((Patient) apt.getPatient()).getHealthCondition() + ")");
            System.out.println();
        }

        scanner.close();
    }
}
