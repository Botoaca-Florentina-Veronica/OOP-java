import java.util.*;

interface Risky {
    double getRisk();
}

class Member {
    private String name;
    private int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

abstract class Project implements Risky {
    private String title;
    private String objective;
    private long funds;
    private Member[] members = new Member[15];
    private Member manager;
    private int memberCount = 0;

    public Project(String title, String objective, long funds, Member manager) {
        this.title = title;
        this.objective = objective;
        this.funds = funds;
        this.manager = manager;
        this.memberCount = 0;
    }

    public void addMember(Member member) {
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

    public CommercialProject(String title, String objective, long funds, Member manager,
                             String deadline, long marketingFunds, int teams) {
        super(title, objective, funds, manager); //trebuie sa am acelasi numar de parametrii
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

    public MilitaryProject(String title, String objective, long funds, Member manager, String password) {
        super(title, objective, funds, manager);
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

    public OpenSourceProject(String title, String objective, long funds, Member manager, String mailingList) {
        super(title, objective, funds, manager);
        this.mailingList = mailingList;
    }

    @Override
    public double getRisk() {
        // implementarea calculului de risc specific proiectului open-source
        return (double) getMemberCount() / getFunds();
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

        int i, min=0;
        for (i=1; i<projectCount; i++) {
            if (projects[i].getRisk() < projects[min].getRisk()) {
                projects[min] = projects[i];
            }
        }
        return projects[min];
    }

    public static void main(String[] args) {

        InvestmentCompany company = new InvestmentCompany(6);

        Member m1 = new Member("Vera", 30);
        Member m2 = new Member("Ana", 35);
        Member m3 = new Member("Sandra", 40);
        Member p1 = new Member("Andreea", 25);
        Member p2 = new Member("Alex", 37);
        Member p3 = new Member("Larisa", 20);
        Member p4 = new Member("Luca", 19);
        Member p5 = new Member("Marius", 31);
        Member p6 = new Member("Armina", 27);
        Member p7 = new Member("Diana", 26);

        CommercialProject c1 = new CommercialProject("Project1", "Objective1", 100000, m1, "2023-12-31", 50000, 2);
        c1.addMember(p1);
        c1.addMember(p2);
        System.out.println(c1.getRisk());

        MilitaryProject ml1 = new MilitaryProject("Project2", "Objective2", 150000, m2, "password123");
        ml1.addMember(p3);
        ml1.addMember(p4);
        System.out.println(ml1.getRisk());

        OpenSourceProject o1 = new OpenSourceProject("Project3", "Objective3", 80000, m3, "project3-list");
        o1.addMember(p5);
        o1.addMember(p6);
        o1.addMember(p7);
        System.out.println(o1.getRisk());


        company.addProject(c1);
        company.addProject(ml1);
        company.addProject(o1);

        Project bestInvestment = company.getBestInvestment();

        if (bestInvestment != null) {
            System.out.println("Best Investment: " + bestInvestment.getRisk());
        } else {
            System.out.println("No projects available.");
        }
    }
}
