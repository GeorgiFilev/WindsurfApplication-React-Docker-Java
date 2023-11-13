

describe("Register in form test", function(){
    beforeEach(function(){
      cy.visit("");
    });

    it("go to register",()=>{
      cy.visit("/register");
    });
    it("register OK credentials",()=>{
      cy.visit("/register");
      const username ="Ivan";
      const email ="ivan@gmail.com";
      const password ="123456";
      
      cy.server();
      cy.route("POST","http://localhost:8080/auth/signup",{username,email,password}).as("registerRequest");

      cy.get(".username").type(username);
      cy.get(".email").type(email);
      cy.get(".password").type(password);
      cy.get(".passwordRepeat").type(password);
      cy.get(".btn-large").click();
      cy.wait("@registerRequest");
      cy.url().should("include","/register");
    });
});