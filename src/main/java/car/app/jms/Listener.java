package car.app.jms;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import car.app.entity.Car;
import car.app.services.CarService;

@MessageDriven(mappedName = "jms/CarAppQueue")
public class Listener implements MessageListener {
	
	@EJB(name = "carService")
	CarService carService;

	Logger log = Logger.getLogger("Listener");

	@Override
	public void onMessage(Message m) {

		TextMessage msg = (TextMessage) m;
		ObjectMapper Obj = new ObjectMapper();
		try {
			 
			String c = m.getStringProperty("CASE");
			String carJson = msg.getText();
			Car car = Obj.readValue(carJson, Car.class);
			switch (c) {
			case "CREATE":
				carService.addCar(car);
				break;

			case "UPDATE":
				carService.updateCar(car);
				break;

			case "DELETE":
				int id = car.getId();
				carService.deleteCar(id);
				break;

			default:
				break;
			}
			log.info("message to "+ c.toLowerCase() +" received: " + carJson);
		} catch (Exception e) {
			log.error(e);
		}
	}

}