public class test2 {
    public static void main(String[] args) {
        appUsers user = new appUsers("John", "Smith", "johnsmith@gmail.com", "johnsmith", "password", "profilepic");
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getEmail());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getProfilepic());
        System.out.println(user.getId());
        System.out.println(user.getRoleId());

        appUsers user2 = new appUsers("Jane", "Doe", "janedoe@gmail.com", "janedoe", "password", "profilepic");
        System.out.println(user2.getFirstName());
        System.out.println(user2.getLastName());
        System.out.println(user2.getEmail());
        System.out.println(user2.getUsername());
        System.out.println(user2.getPassword());
        System.out.println(user2.getProfilepic());
        System.out.println(user2.getId());
        System.out.println(user2.getRoleId());

        appUsers user3 = new appUsers("Johnny", "Doe", "jony@gmail.com", "jony", "password", "profilepic");
        System.out.println(user3.getFirstName());
        System.out.println(user3.getLastName());
        System.out.println(user3.getEmail());
        System.out.println(user3.getUsername());
        System.out.println(user3.getPassword());
        System.out.println(user3.getProfilepic());
        System.out.println(user3.getId());
        System.out.println(user3.getRoleId());
    }
}
