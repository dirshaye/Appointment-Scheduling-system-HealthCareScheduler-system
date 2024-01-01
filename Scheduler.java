import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Person {
    private String name;
    private String contactNumber;

    public Person(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    static class ContactInformation {
        private String email;

        public ContactInformation(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }
    }
}

interface AppointmentScheduler {
    void scheduleAppointment(Appointment appointment);

    void cancelAppointment(Appointment appointment);

    List<Appointment> viewAppointments();
}

class Appointment {
    private String date;
    private Person doctor;
    private Person patient;

    public Appointment(String date, Person doctor, Person patient) {
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }

    public Person getDoctor() {
        return doctor;
    }

    public Person getPatient() {
        return patient;
    }
}

class Doctor extends Person {
    private String specialization;

    public Doctor(String name, String contactNumber, String specialization) {
        super(name, contactNumber);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }
}

class Patient extends Person {
    private String healthCondition;

    public Patient(String name, String contactNumber, String healthCondition) {
        super(name, contactNumber);
        this.healthCondition = healthCondition;
    }

    public String getHealthCondition() {
        return healthCondition;
    }
}

class Scheduler implements AppointmentScheduler {
    private List<Appointment> appointments;

    public Scheduler() {
        this.appointments = new ArrayList<>();
    }

    @Override
    public void scheduleAppointment(Appointment appointment) {
        if (isAppointmentValid(appointment)) {
            appointments.add(appointment);
            System.out.println("Appointment scheduled successfully.");
        } else {
            System.out.println("Unable to schedule appointment. Check for conflicts or invalid inputs.");
        }
    }

    @Override
    public void cancelAppointment(Appointment appointment) {
        if (appointments.remove(appointment)) {
            System.out.println("Appointment canceled successfully.");
        } else {
            System.out.println("Appointment not found. Unable to cancel.");
        }
    }

    @Override
    public List<Appointment> viewAppointments() {
        return new ArrayList<>(appointments);
    }

    private boolean isAppointmentValid(Appointment appointment) {
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scheduler scheduler = new Scheduler();
     try{
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

        Appointment appointment = new Appointment(appointmentDate, doctor, patient);

        scheduler.scheduleAppointment(appointment);
        }
        catch(IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
        finally{
            scanner.close();
        }
        List<Appointment> allAppointments = scheduler.viewAppointments();
        System.out.println("All Appointments:");
        for (Appointment apt : allAppointments) {
            System.out.println("Date: " + apt.getDate());
            System.out.println("Doctor: " + apt.getDoctor().getName() + " (Specialization: " + ((Doctor) apt.getDoctor()).getSpecialization() + ")");
            System.out.println("Patient: " + apt.getPatient().getName() + " (Condition: " + ((Patient) apt.getPatient()).getHealthCondition() + ")");
            System.out.println();
        }
    }
}
