// HW 6
// Burgard Lu (jl4nq)
// Source: Professor Basit's OH
// Piazza
public class Button extends Provided.AbstractButton{


	public Button(int floor, Provided.AbstractElevatorController control) {
		super(floor, control);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void press(Provided.Person p) {// a person has pressed the button, the floor that requested the elevator and the person that requested the elevator are passed to the controller, and then to the hail method. 
		this.control.request(floor, p);
		this.control.lock.lock();
		this.control.buttonPressed.signalAll(); // signal that button has been pressed 
		this.control.lock.unlock();
	}

}
