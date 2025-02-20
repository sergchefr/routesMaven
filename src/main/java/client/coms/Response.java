package client.coms;
/** Объект, содержащий ответ от серверной части*/
public class Response {
    private final String resp;

    public Response(String responce) {
        this.resp = responce;
    }

    public String getMessage() {
        return resp;
    }
}
