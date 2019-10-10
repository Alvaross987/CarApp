package car.app.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4595904639796754378L;

	public DataNotFoundException(String car) {
		super(car);
	}
		
}
