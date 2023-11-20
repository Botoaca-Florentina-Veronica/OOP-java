interface Risky {
   public double getRisk();
}

class Programmer {
    private String name;
    private int age;

    public Programmer(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Project implements Risky {
    private String title;
    private String objective;
    private long funds;
    private Programmer[] members;
    private Programmer manager;
    private int memberCount = 0;
    private int maxMembers = 0;

    public Project(String title, String objective, long funds, Programmer manager, int maxMembers) {
        this.title = title;
        this.objective = objective;
        this.funds = funds;
        this.manager = manager;
        this.members = new Programmer[maxMembers];
        this.memberCount = 0;
    }

    public void addMember(Programmer member) {
        if (memberCount < members.length) {
            members[memberCount++] = member;
        }
    }

    public long getFunds() {
        return funds;
    }

    public int getMemberCount() {
        return memberCount;
    }

    @Override
    public double getRisk() {
        // implementarea calculului de risc specific proiectului
        return (double) memberCount / funds; // exemplu simplu pentru moment
    }
}

class CommercialProject extends Project {
    private String deadline;
    private long marketingFunds;
    private int teams;

    public CommercialProject(String title, String objective, long funds, Programmer manager,
                             String deadline, long marketingFunds, int teams) {
        super(title, objective, funds, manager, 15); //trebuie sa am acelasi numar de parametrii
        //ca in constructorul de la projects, pe care il extindem aici
        this.deadline = deadline;
        this.marketingFunds = marketingFunds;
        this.teams = teams;
    }

    @Override
    public double getRisk() {
        // implementarea calculului de risc specific proiectului comercial
        return (double) (teams * 3) / (getMemberCount() * getFunds() - marketingFunds);
    }
}

class MilitaryProject extends Project {
    private String password;

    public MilitaryProject(String title, String objective, long funds, Programmer manager, String password) {
        super(title, objective, funds, manager, 15);
        this.password = password;
    }

    @Override
    public double getRisk() {
        // implementarea calculului de risc specific proiectului militar
        return (double) getMemberCount() / (password.length() * getFunds()); // exemplu simplu pentru moment
    }
}

class OpenSourceProject extends Project {
    private String mailingList;

    public OpenSourceProject(String title, String objective, long funds, Programmer manager, String mailingList) {
        super(title, objective, funds, manager, 1000);
        this.mailingList = mailingList;
    }

    @Override
    public double getRisk() {
        // implementarea calculului de risc specific proiectului open-source
        return (double) getMemberCount() / getFunds(); // exemplu simplu pentru moment
    }
}

class InvestmentCompany {
    private Project[] projects;
    private int projectCount = 0;
    private int maxProjects;

    public InvestmentCompany(int maxProjects) {
        this.projects = new Project[maxProjects];
        this.maxProjects = maxProjects;
    }

    public void addProject(Project project) {
        if (projectCount < projects.length) {
            projects[projectCount++] = project;
        }
    }

    public Project getBestInvestment() {
        if (projectCount == 0) {
            return null; // Nu există proiecte
        }

        Project bestInvestment = projects[0];
        double lowestRisk = bestInvestment.getRisk();

        for (int i = 1; i < projectCount; i++) {
            double currentRisk = projects[i].getRisk();
            if (currentRisk < lowestRisk) {
                bestInvestment = projects[i];
                lowestRisk = currentRisk;
            }
        }

        return bestInvestment;
    }

    public static void main(String[] args) {
        InvestmentCompany company = new InvestmentCompany(3);

        Programmer manager1 = new Programmer("Manager1", 30);
        Programmer manager2 = new Programmer("Manager2", 35);
        Programmer manager3 = new Programmer("Manager3", 40);

        Project project1 = new CommercialProject("Project1", "Objective1", 100000, manager1, "2023-12-31", 50000, 2);
        Project project2 = new MilitaryProject("Project2", "Objective2", 150000, manager2, "password123");
        Project project3 = new OpenSourceProject("Project3", "Objective3", 80000, manager3, "project3-list");

        company.addProject(project1);
        company.addProject(project2);
        company.addProject(project3);

        Project bestInvestment = company.getBestInvestment();

        if (bestInvestment != null) {
            System.out.println("Best Investment: " + bestInvestment.getRisk());
        } else {
            System.out.println("No projects available.");
        }
    }
}
