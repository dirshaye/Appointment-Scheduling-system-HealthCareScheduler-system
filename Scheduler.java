import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

// Abstract class representing a Person
abstract class Person {
    private String name;
    private String contactNumber;

    // Constructor for initializing Person's name and contact number
    public Person(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    // Getter method for retrieving the name
    public String getName() {
        return name;
    }

    // Getter method for retrieving the contact number
    public String getContactNumber() {
        return contactNumber;
    }

    // Static nested class representing contact information
    static class ContactInformation {
        private String email;

        // Constructor for initializing email
        public ContactInformation(String email) {
            this.email = email;
        }

        // Getter method for retrieving the email
        public String getEmail() {
            return email;
        }
    }
}

// Interface for Appointment Scheduler functionality
interface AppointmentScheduler {
    // Method to schedule an appointment
    void scheduleAppointment(Appointment appointment);
    // Method to view all appointments
    List<Appointment> viewAppointments();
}

// Class representing an Appointment
class Appointment {
    private String date;
    private Person doctor;
    private Person patient;

    // Constructor for initializing appointment details
    public Appointment(String date, Person doctor, Person patient) {
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
    }

    // Getter method for retrieving the date
    public String getDate() {
        return date;
    }

    // Getter method for retrieving the doctor
    public Person getDoctor() {
        return doctor;
    }

    // Getter method for retrieving the patient
    public Person getPatient() {
        return patient;
    }
}

// Class representing a Doctor, extending Person
class Doctor extends Person {
    private String specialization;

    // Constructor for initializing doctor details
    public Doctor(String name, String contactNumber, String specialization) {
        super(name, contactNumber);
        this.specialization = specialization;
    }

    // Getter method for retrieving the specialization
    public String getSpecialization() {
        return specialization;
    }
}

// Class representing a Patient, extending Person
class Patient extends Person {
    private String healthCondition;

    // Constructor for initializing patient details
    public Patient(String name, String contactNumber, String healthCondition) {
        super(name, contactNumber);
        this.healthCondition = healthCondition;
    }

    // Getter method for retrieving the health condition
    public String getHealthCondition() {
        return healthCondition;
    }
}

// Class implementing AppointmentScheduler interface
class Scheduler implements AppointmentScheduler {
    private List<Appointment> appointments;

    // Constructor for initializing Scheduler
    public Scheduler() {
        this.appointments = new ArrayList<>();
    }

    // Method to schedule an appointment
    @Override
    public void scheduleAppointment(Appointment appointment) {
        if (isAppointmentValid(appointment)) {
            appointments.add(appointment);
            System.out.println("Appointment scheduled successfully.");
        } else {
            System.out.println("Unable to schedule appointment. Check for conflicts or invalid inputs.");
        }
    }

    // Method to view all appointments
    @Override
    public List<Appointment> viewAppointments() {
        return new ArrayList<>(appointments);
    }

    // Private method to check if an appointment is valid
    private boolean isAppointmentValid(Appointment appointment) {
        // Perform validation logic here
        if (appointment.getDate() == null || appointment.getDoctor() == null || appointment.getPatient() == null) {
            // Invalid appointment if any required information is missing
            return false;
        }
        // Additional validation logic...
        // If all validation checks pass, the appointment is considered valid
        return true;
    }

    // Main method for testing the Scheduler functionality
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scheduler scheduler = new Scheduler();
        while (true) {
            // Collecting information for a new appointment
            System.out.print("Enter Doctor's Name: ");
            String doctorName = scanner.nextLine();
            System.out.print("Enter Doctor's Contact Number: ");
            String doctorContactNumber = scanner.nextLine();
            System.out.print("Enter Doctor's Specialization: ");
            String doctorSpecialization = scanner.nextLine();
            Person doctor = new Doctor(doctorName, doctorContactNumber, doctorSpecialization);

            System.out.print("Enter Patient's Name: ");
            String patientName = scanner.nextLine();
            System.out.print("Enter Patient's Contact Number: ");
            String patientContactNumber = scanner.nextLine();
            System.out.print("Enter Patient's Health Condition: ");
            String patientHealthCondition = scanner.nextLine();
            Person patient = new Patient(patientName, patientContactNumber, patientHealthCondition);

            System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
            String appointmentDate = scanner.nextLine();

            // Creating a new appointment
            Appointment appointment = new Appointment(appointmentDate, doctor, patient);

            // Scheduling the appointment
            scheduler.scheduleAppointment(appointment);

            // Ask the user if they want to continue scheduling
            System.out.print("Do you want to schedule another appointment? (yes/no): ");
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
            System.out.println("Patient: " + apt.getPatient().getName() + " (Condition: " + ((Patient) apt.getPatient()).getHealthCondition() + ")");
            System.out.println();
        }

        scanner.close();
    }
}
