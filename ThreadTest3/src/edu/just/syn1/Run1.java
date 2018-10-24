package edu.just.syn1;

class PublicClass1 {

    static private String username;
    static private String password;

    static class PrivateClass {

        private String age;
        private String address;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void printPublicProperty() {
            System.out.println(username + " " + password);
        }
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        PublicClass1.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        PublicClass1.password = password;
    }

}

public class Run1 {

    public static void main(String[] args) {
        PublicClass1 publicClass1 = new PublicClass1();
        publicClass1.setUsername("usernameValue");
        publicClass1.setPassword("passwordValue");
        System.out.println(publicClass1.getUsername() + " " + publicClass1.getPassword());

        PublicClass1.PrivateClass privateClass = new PublicClass1.PrivateClass();
        privateClass.setAge("ageValue");
        privateClass.setAddress("addressValue");
        System.out.println(privateClass.getAge() + " " + privateClass.getAddress());
    }

}
