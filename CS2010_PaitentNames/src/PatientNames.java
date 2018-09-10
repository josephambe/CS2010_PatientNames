// Copy paste this Java Template and save it as "PatientNames.java"
import java.util.*;
import java.io.*;
import java.util.TreeSet;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2018 hash code: wrMQ8UMcPU5q7F4UPNhT (do NOT delete this line)

class PatientNames {
    // if needed, declare a private data structure here that
    // is accessible to all methods in this class

    // --------------------------------------------
    BinaryTree malePatients;
    BinaryTree femalePatients;

    // --------------------------------------------

    public PatientNames() {
        // Write necessary code during construction;
        //
        // write your answer here

        // --------------------------------------------
        malePatients = new BinaryTree();
        femalePatients = new BinaryTree();
        // --------------------------------------------
    }

    void AddPatient(String patientName, int gender) {
        // You have to insert the information (patientName, gender)
        // into your chosen data structure
        //
        // write your answer here

        // --------------------------------------------
        if(gender == 1){
            malePatients.addNode(patientName.substring(0,1), patientName, gender,0);
        } else {
            femalePatients.addNode(patientName.substring(0,1), patientName, gender,0);
        }

        // --------------------------------------------
    }

    void RemovePatient(String patientName) {
        // You have to remove the patientName from your chosen data structure
        //
        // write your answer here

        // --------------------------------------------
        Node toRemove = malePatients.findPatient(patientName);

        if(toRemove == null){
            toRemove = femalePatients.findPatient(patientName);
            femalePatients.remove(toRemove);
            return;
        }
        malePatients.remove(toRemove);
        // --------------------------------------------
    }

    int Query(String START, String END, int gender) {

        // You have to answer how many patient name starts
        // with prefix that is inside query interval [START..END)
        //
        // write your answer here

        // --------------------------------------------
        int numMales = 0;
        int numFemales = 0;

        if(gender == 1 || gender == 0){
            int endValue = malePatients.rank(malePatients.root, END);
            int startValue = malePatients.rank(malePatients.root, START);
            numMales = endValue - startValue;
        }

        if(gender == 2 || gender == 0) {
            int endValue = femalePatients.rank(femalePatients.root, END);
            int startValue = femalePatients.rank(femalePatients.root, START);
            numFemales = endValue - startValue;
        }

        if(gender == 1){
            return numMales;
        } else if(gender == 2){
            return numFemales;
        } else {
            return numMales + numFemales;
        }

        // --------------------------------------------
    }

    void run() throws Exception {
        // do not alter this method to avoid unnecessary errors with the automated judging
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 0) // end of input
                break;
            else if (command == 1) // AddPatient
                AddPatient(st.nextToken(), Integer.parseInt(st.nextToken()));
            else if (command == 2) // RemovePatient
                RemovePatient(st.nextToken());
            else // if (command == 3) // Query
                pr.println(Query(st.nextToken(), // START
                        st.nextToken(), // END
                        Integer.parseInt(st.nextToken()))); // GENDER
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception {
        // do not alter this method to avoid unnecessary errors with the automated judging
        PatientNames ps2 = new PatientNames();
        ps2.run();
    }
}
