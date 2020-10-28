package bpdts.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3781719401516208328L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
