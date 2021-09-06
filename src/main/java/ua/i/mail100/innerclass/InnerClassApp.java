package ua.i.mail100.innerclass;

public class InnerClassApp {
    public static void main(String[] args) {
        BusinessErrorRestResponse response = new BusinessErrorRestResponse();
        response.setError("324", "234");
        System.out.println(response.getErrorUserCode());
    }
}
