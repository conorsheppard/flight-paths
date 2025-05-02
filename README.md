Because of the bounded scope of the challenge I have decided to keep everything in one class for simplicity rather
than bloating the project with extra classes.

I wanted to keep the findAllPathsCollect method signature as svelt or unbusy as possible so I opted to store the costs
map at the class level, the trade-off being that to use a new costs map, you'd have to create a new FlightPathsCalculator.
However, I think this improves readability and you can still do as many calculations with different departure and destination
points as you wish on that map.

Results are in the order they are retrieved by the flight costs calculator, not by cost although this could be easily updated.



### How to run

Docker

Java
