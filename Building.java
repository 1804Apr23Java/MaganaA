
public class Building {
	private String building;
	private String foundation;
	private String framework;
	private String roof;


	
		public Building() {
			String uc = " is under Construction";
			this.building = "Building" +uc;
			this.foundation = "Foundation" + uc;
			this.framework ="Framework" + uc;
			this.roof = "Roof"+ uc;

		}
		
		//prints out the status of the building 
		public String constructionStatus() {
			return building + "\n" + foundation +"\n"+ framework +"\n"+ roof + "\n";
			
		}
		
		public void buildFoundation() {
			this.foundation = "Foundation is complete";
		}
		public void buildFoundation(String material) {
			this.foundation = "The " + material + " foundation is complete";
		}
		
		
		public void buildFramework() {
			this.framework = "Framework is complete.";
		}
		public void buildFramework(String material) {
			this.framework = "The " + material + " framework is complete";
		}
		
		public void buildRoof() {
			this.roof = "The Roof is complete";
			this.built();
			
		}
		public void buildRoof(String material) {
			this.roof = "The " + material + " roof is complete";
			this.built();
		}
		private void built() {
			this.building = "Building is complete";
		}
		
}
