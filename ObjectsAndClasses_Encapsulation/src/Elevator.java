public class Elevator
{
    public int currentFloor = 1;
    public int minFloor;
    public int maxFloor;

    public Elevator(int minFloor, int maxFloor)
    {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }
    public int getCurrentFloor () {
        return currentFloor;
    }
    public void moveUp () {
        //currentFloor = currentFloor < maxFloor ? currentFloor += 1 : currentFloor ;
        if (currentFloor < maxFloor)
            if (currentFloor == -1) currentFloor = 1; else currentFloor += 1;
    }
    public void moveDown () {
        //currentFloor = currentFloor > minFloor ? currentFloor -= 1 : currentFloor ;
        if (currentFloor > minFloor)
            if (currentFloor == 1) currentFloor = -1; else currentFloor -= 1;
    }

    public void move (int floor)
    {
        if ( floor < minFloor || floor > maxFloor || floor == 0 ) {
            System.out.println("Задан некорректный номер этажа");
        } else {
            while (currentFloor != floor)
            {   if (currentFloor < floor) moveUp(); else moveDown();
                System.out.println(currentFloor);
            }
        }

    }

}
