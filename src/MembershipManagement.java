import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);

    private int getIntIntput() {
        int choice = 0;
        try {
            choice = reader.nextInt();
            if (choice == 0) throw new InputMismatchException();
            reader.nextLine();
        } catch (InputMismatchException exception) {
            reader.nextLine();
            System.out.println("ERROR: invalid input. Try again");
            reader.nextLine();
        }
        return choice;
    }
    private void printClubOptions(){
        System.out.println("\n1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi Clubs");
    }
    int getChoice(){
        int choice;
        System.out.println("\n Welcome to ozone fitness center");
        System.out.println("==================================");
        System.out.println("1) Add Member");
        System.out.println("2) Remove Member");
        System.out.println("3) Display Member Information");
        System.out.println("Please select an option (or Enter -1 to quit)");
        choice = getIntIntput();
        return choice;

    }
public String addMembers(LinkedList<Member> listMembers){
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> cal;
        System.out.print("\n Please enter the members name: ");
        name = reader.nextLine();

        printClubOptions();

        System.out.print("\n Please enter the members clubID: ");
        club = getIntIntput();

        while (club<1 || club < 4){
            System.out.println("\n Invalid cloubID/ Please try again: ");
            club = getIntIntput();
        }
        if (listMembers.size() > 0) {
            memberID = listMembers.getLast().getMemberID() + 1;
        }else{
            memberID = 1;
        }
        if (club !=4) {
            cal = (n) -> {
                switch (n) {
                    case 1:
                        return 900;
                    case 2:
                        return 950;
                    case 3:
                        return 1000;
                    default:
                        return -1;

                }
            };


            fees = cal.calculateFees(club);
            mbr = new SingleClubMember('S', memberID, name, fees, club);
            listMembers.add(mbr);
            mem = mbr.toString();
            System.out.println("\n Status: Single club member adder");


        }else{
            cal = (n) ->{
                if (n==4) {
                    return 1200;
                }else{
                    return -1;

                }
            };
            fees = cal.calculateFees(club);
            mbr = new MultiClubMember('M', memberID, name, fees,100);
            listMembers.add(mbr);
            mem = mbr.toString();
            System.out.println("\n Status: Multi club member adder");
        }
         return mem;

}
public void removeMember(LinkedList<Member> listMembers){
        int memberID;
        System.out.println("\n Enter Member Id to remove:");
        memberID = getIntIntput();
        for(int i=0; i < listMembers.size(); i++){
            if (listMembers.get(i).getMemberID()==memberID){
                listMembers.remove(i);
                System.out.println("\n Member removed");
            }
        }
        System.out.println("\n Member  not found");
}
public void printMemberInfo(LinkedList<Member> listMembers) {
    int memberID;
    System.out.println("\n Enter member ID to remove");
    memberID = getIntIntput();
    for (int i = 0; i < listMembers.size(); i++) {
        if (listMembers.get(i).getMemberID() == memberID){
            String[] memberInfo = listMembers.get(i).toString().split(",");
        System.out.println("\n Member type: " + memberInfo[0]);
        System.out.println("\n Member ID: " + memberInfo[1]);
        System.out.println("\n Member Name: " + memberInfo[2]);
        System.out.println("\n Member fees: " + memberInfo[3]);
        if (memberInfo[0].equals("S")) {
            System.out.println("\n ClubID: " + memberInfo[4]);
        } else {
            System.out.println("Member Ship points: " + memberInfo[4]);
        }
        return;
    }
}
    System.out.println("\n Member ID not found");

}

}




