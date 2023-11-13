// import { describe } from "mocha";

describe('Load form', function(){
  it("Opens the home/main page", ()=>{
    cy.visit("");
  });
});
describe('going through navbar test', () => {
  it('Clicks the register "button"', () => {
      cy.visit('');
      cy.get('.signIn').click();
      cy.url().should("include", "/register");

      cy.get('.logIn').click();
      cy.url().should("contain","/login");
  });
});