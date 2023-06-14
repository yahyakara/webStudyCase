package studyCase.constants;

public enum EndPoint {
    HOME_PAGE("/"),
    HOME_ELECTRONIC_BOUTIQUES("/butik/liste/5/elektronik"),
    LOGIN_PAGE("/giris");
    public final String url;

    EndPoint(String url) {
        this.url = url;
    }

}
