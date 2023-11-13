import axios from "axios";
import Cookies from 'js-cookie';

const API_URL = "http://localhost:8080/auth/";
class AuthService {
  login(username, password) {
    return axios
      .post(API_URL + "signin", {
        username,
        password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        Cookies.set("user_id",response.data.id,{expires: 700000});
        Cookies.set("user_username",response.data.username,{expires: 700000});
        Cookies.set("user_roles",response.data.roles,{expires:700000} );
        return response.data;
      });
  }

  logout =() => {
    localStorage.removeItem("user");
    Cookies.remove('user');
    Cookies.remove('user_username');
    Cookies.remove('role');
    Cookies.remove('user_id');
    Cookies.remove('user_roles');
    
  }

  register(username, email, password) {
    return axios.post(API_URL + "signup", {
      username,
      email,
      password
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));
  }

  getUserByID =(id) => {
    return axios.get("http://localhost:8080/people/" + id);
  }
}

export default new AuthService();