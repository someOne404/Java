// HW 6
// Burgard Lu (jl4nq)
// Source: Professor Basit's OH
// Piazza
public class ElevatorController extends Provided.AbstractElevatorController{
	@Override
	public void run() {
		try {
			lock.lock();
			while (!Provided.TERMINATE) {
				while (this.elevatorAvailable() == null && !floorQueue.isEmpty()) { // If no elevator is available and someone has requested the elevator, wait for some elevator to finish
					elevatorFinished.await(); 
				}
				while (floorQueue.isEmpty()) { // If no one presses the button, wait for someone to press the button
					buttonPressed.await();
				}
				try {
					if (this.elevatorAvailable() != null) { // If there is some elevator available, that elevator goes to the floor where the person is at to pick the person 
						this.elevatorAvailable().hail(floorQueue.remove(), personQueue.remove());
					}
				}catch (Provided.OccupiedException e) {
					e.printStackTrace();
				}
			}
		} catch(InterruptedException e) {
			System.out.println("Controller Interrupted");
		}
		finally {
			lock.unlock();
		}
	}

	public Provided.AbstractElevator elevatorAvailable() { // To seek for an available elevator that can pick up someone  
		for (Provided.AbstractElevator e: elevators) {
			if (e.isAvailable()) return e;
		}
		return null;
	}



}





