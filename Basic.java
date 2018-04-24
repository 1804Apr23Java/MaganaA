public class Basic {
    
    public static void main(String[] args) {
    	
    	Carpenter john = new Carpenter("John", 1);
        Inspector tom = new Inspector("Tom", 2);
        Laborer janet = new Laborer("Janet", 3);
        john.calculateSalary();
        System.out.println(john.getSalary());
        System.out.println(john.getName());
        System.out.println(tom.getSalary());        
    	
        Building basic = new Building();
        System.out.println(basic.constructionStatus());
        basic.buildFoundation();
        System.out.println(basic.constructionStatus());
        basic.buildFramework();
        System.out.println(basic.constructionStatus());
        basic.buildRoof("wood");
        System.out.println(basic.constructionStatus());
     
    }

}