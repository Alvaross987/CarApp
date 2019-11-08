package car.app.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = -5248838767786820499L;

	public DataNotFoundException(String car) {
		super(car);
	}
		
}
