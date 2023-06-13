package studyCase.constants;

public enum EndPoint {
    HOME_PAGE("/"),
    LOGIN_PAGE("/giris");
    public final String url;

    EndPoint(String url) {
        this.url = url;
    }

}
