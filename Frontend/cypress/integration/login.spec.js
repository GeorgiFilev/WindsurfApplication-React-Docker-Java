describe("LogIn form test", function(){
  
  context("successful log in try", function(){
    beforeEach(function(){
      cy.visit("");
    });
    
    it("logIn test", ()=>{
      cy.visit("/login");
      cy.server();

      cy.route("POST","http://localhost:8080/auth/signin", {username,password}).as("loginTry");

      const username = "Lazar";
      const password = "123456";
      cy.get(".username").type(username);
      cy.get(".password").type(password);

      cy.get(".btn-large").click();

      cy.wait("@loginTry");
      cy.url().should("contain","");
    });
  });
 
});