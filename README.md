# Team-and-Project-Management-System
This is a simplified management system for a company to handle the working teams, employees and project assignments. The company forms teams to work for projects. Each team has a leader when it is formed. Then other employees may be assigned to join the team as normal team members.


For simplicity, we have the following assumptions and rules:

• Public holidays and any non-working weekends are ignored. That is, for example, the period during 20-Mar to 29-Mar is counted as 10 working days.

• An employee cannot belong to two teams at the same time. That is, an employee can belong to one team only, either as the leader or a normal member.

• A normal member of a team can change to another team. However, a team leader will never leave his team.

• To run a project, the company first creates the project with the project code and provides an estimation of the manpower needed by the project (in term of a whole number of man-days).
Then the project is assigned to a team with a start day. The expected end day will be calculated immediately based on the team size (how many members, including the team leader) at the moment when the project is assigned. Fractional parts will be rounded-up. For example, a project of 4 man- days (estimated), if assigned to a team of 3 persons, will take 2 whole days.
For example, X Troop was assigned to take P002 and then P003:
P002 (10 man-days) was assigned to X Troop when there was only 1 person (the leader) in X Troop P003 (10 man-days) was assigned to X Troop when there were 2 persons in X Troop

Once assigned, the duration of a project is not supposed to change even if employees are added to or leave the team. ( If a team gets lack of manpower, then everybody needs to work harder (because projects cannot wait), or the management have to hire new employees or to move some existing employees into the team. -- The management will decide. Therefore our program does not need to handle specificially. )

• Each project is to be assigned to one working team only. Each team can work for at most 1 project at a time. That is, the working periods cannot overlap: the start day of the next project must be later than the end day of its previous project.
Also, the earliest start day of a project is tomorrow (i.e., the next day of the SystemDate). That is, we cannot assign a project immediately on today (or any previous day).