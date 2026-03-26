package d.scerbinkinas.PulseDesk.exception;


public class TicketNotFoundException extends RuntimeException {
  public TicketNotFoundException(String message) {
    super(message);
  }
}
