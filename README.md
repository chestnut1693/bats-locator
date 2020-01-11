# Bats Locator
This plugin will highlight chests that are most likely to contain bats or poison. From all the chests only three contain poison and one contains bats, called a solution set. The solution sets are predefined by Jagex and it is not certain that solution sets always contain three chests with poison but it does seem like it.

The chests are numbered starting from 0 and incremented with chests starting from near to far and left to right when facing the entrance of the thieving room. This is a bit different from the existing online tools but it is consistent. This plugin allows setting the colors for chests that contain poison or bats. Setting the color for chests containing grubs is not possible as dots of those chests are not shown. The dots of chests that are most likely to contain poison or bats are not transparent and are a little larger than the other dots. The transparency and the size of the overall dots can be changed through the settings.

Initial state of the room with a left turn.
![image](https://user-images.githubusercontent.com/47870624/71720983-c27e1f80-2e23-11ea-9c7c-9af7263dd45b.png)

State of the room when a poison chest has been found.
![image](https://user-images.githubusercontent.com/47870624/71721430-52709900-2e25-11ea-95b7-005a804b3f75.png)

State of the room when the bats chest has been found, in this case all three poison chests were found before the bats chest.
![image](https://user-images.githubusercontent.com/47870624/71721401-281edb80-2e25-11ea-9338-62cb9f71b575.png)

Finding a poison or bats chest will mark all other chests as grubs that are not in the same solution sets as the poison or bats chest. This is all based on the solution sets given by [Not Noob](https://dikkenoob.github.io/) and [Endless Dawn](https://docs.google.com/spreadsheets/d/1eEf8yI_MG6cKuIXSqsMKO8YfuQSn5pxWtGWxSDaqUsA) which were transformed manually and mistakes could have been made. It could happen that the chests in the thieving room are not yet supported by the solution sets. If this happens the bats locator will fallback and hide the found chests containing grubs and show the found chests containing poison or bats. The poison and bats chets will show their number instead of a dot disregarding the settings. It would be nice if missing or incorrect solution sets are reported through an issue and it is advised to find three poison chests and one bats chest before reporting.

A bats chest has been found in the room with a right turn. 
![image](https://user-images.githubusercontent.com/47870624/71723121-bc8c3c80-2e2b-11ea-9fbf-4f7dc51593f7.png)

A chest is opened containing poison which was not expected by the bats locator so it fallsback. None of the other chests were opened otherwise they would not show a dot.
![image](https://user-images.githubusercontent.com/47870624/71723147-d3329380-2e2b-11ea-8586-599722e41fc2.png)

Chest numbers for the [right turn](https://user-images.githubusercontent.com/47870624/71784595-49cebd00-2ff5-11ea-909f-f06fc2210fb9.png), [left turn](https://user-images.githubusercontent.com/47870624/71784591-3cb1ce00-2ff5-11ea-9802-b5257508edfe.png) and [straight](https://user-images.githubusercontent.com/47870624/71784556-f52b4200-2ff4-11ea-8822-dda26edcf2ae.png) room.
