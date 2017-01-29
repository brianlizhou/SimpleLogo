API_CHANGES
===================

> This is the link to the lab assignment: [API CHANGES](http://www.cs.duke.edu/courses/compsci308/fall16/classwork/09_codereview/)

What changes have been made to the API?
-------------------
Changes to the API on the front-end were somewhat expected; as the project slowly came together, parts of our initial design seemed more/less feasible than other options. As a result, from both a design and programmer ease of use perspective, many of our changes 
were motivated by potential benefits and scalability concerns. Overall, the biggest API changes that happened were largely restructuring the outlook of the UI, altering the data transfer method from the user UI inputs to back-end data, and compartmentalizing all of our UI
windows to a single view to allow for us to single out where each component was. Back-end mainly stayed with the original structure. We have the abstract command class and implemented individual class as its own class. 
<br/>
#### Front-End: 
1. From the beginning, we never really layed out the infrastructure regarding the actual format and design structure of the UI, so we somewhat guessed our way long the entire process of development
2. One big API change was the migration from packet-based data transfer regarding the communication between back and front to one that also used observable binding to allow the back end to have instantaneously reflect changes on the UI/front-end
3. In terms of pure front-end, we altered the view structure in creating one primary class for the entire view (Slogo view), and had small independent features/windows for different consoles.
<br/>

#### Back-End:
1. We have more hierarchical structure on command part. We created one argument command, two argument command classes so individual classes inherits from them. We are planning on creating another class for infinite parameter command type.  
2. We create a Data class to store all the variables and turtle objects.
3. The parser's logic is changing as we are allowing more and more commands. 
<br/>

<br/>

Are changes major or minor?
----------------------
From the front-end the changes were largely minor, though the restructuring of the UI is difficult to evaluate. Overall, we altered our API in order to improve the longer term design and scalability othe project in preparation for the extensions.
Most of back-end's changes are minor as well since we are just filling out the details of our original API design. 
<br/>
#### Front-End: 
1. Corresponding to change number one above, the changes are technically major, but in a sense since they were never established from the beginning it's difficult to assess the severity of it. Overall, we never went into detail about the actual look of the view, thereby resulting in the API change
2. Minor, in the sense that it was primarily a design change. We intially thought that the packed based data transfer system would be sufficient, but in the end for design purposes we decided to implement observable bindings for values
3. Minor, we already had the idea established for the view, and just had to implement it; looking back now, it works and keeps things very compartamentalized and allows us to hone in on errors if there are any UI problems.
<br/>

#### Back-End:
1. This design minor change allows us to avoid the duplicated codes. 
2. This is an additional, but neccessary.
3. Minor, doesn't affect our design. 
<br/>
<br/>

Changes better or for worse?
----------------------
We didn't take any shortcuts in our design, so the front-end API changes are largely all beneficial.
<br/>
#### Front-End: 
1. Changes are definitely for the better, as otherwise we wouldn't have a unified structure or design plan for the front-end, resulting in a huge mess
2. Better from a design standpoint, but performance wise probably negligible in its impact
3. Perhaps better from a development standpoint, but worse overall? Having the separated and individualized classes makes it very easy to read and understand what's going on in terms of the front-end display and what the consoles are doing
<br/>

#### Back-End:
1. Definitely improved our design.
2. This is neccessary, so it's a good change.
3. Very insignificant. 
<br/>
<br/>

Foreseeable significant changes in near future? (based on experience and the fact that we know all of the extensions now)
----------------------
No significant changes in the front-end; the most significant changes will be reflected in the back-end, which is directly synchronized with the user display.
#### Front-End: 
1. Nothing notable or significant
2. For the front-end, there aren't many serious changes as the brute work is all done in the back-end. We may need to add in a few additional components like a keyframe to help animate the turtle, but all other extensions likely aren't going to require serious modifications
3. Probably a good change, as in the future for all of the extensions we can simply look at the corresponding class for the console and UI view in order to implement additional extensions
<br/>

#### Back-End:
1. As we are doing more extensions, we might do some changes in the back end. Now we can do allowing multiple turtles without much change in our structure. 
2. For infinite number of parameters, we are planning on doing something same as list check in the parser, and creating a new class for infinite parameter type. 
3. We might need more changes for recursion and other extensions. 
<br/>
<br/>