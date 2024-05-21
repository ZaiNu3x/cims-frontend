package group.intelliboys.cimsfrontend.models;

public class AuthenticationTokenHolder {
    private static String token;

    public AuthenticationTokenHolder(String token) {
        this.token = token;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        AuthenticationTokenHolder.token = token;
    }
}