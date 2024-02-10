import axios from "axios";
import { useRef, useState } from "react";
import { Navigate, Redirect } from "react-router-dom";
import "../styles/login.css";

function Login() {
  // state
  const userName = useRef();
  const password = useRef();
  const [redirectToHome, setRedirectToHome] = useState(false);

  // comportement
  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post(
        "https://gifted-rice-production.up.railway.app/api/connexion",
        {
          userName: userName.current.value,
          password: password.current.value,
        }
      );
      const token = response.data;
      console.log(token.bearer);
      localStorage.setItem("token", token.bearer);
      setRedirectToHome(true);
    } catch (error) {
      console.error("Erreur d' authentification", error.message);
    }
  };

  if (redirectToHome) {
    return <Navigate to="/home" />;
  }

  // affichage
  return (
    <div>
      <div className="container">
        <h2>Login</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <input
              ref={userName}
              type="text"
              name="userName"
              placeholder="Nom utilisateur"
              id="username"
              required
            />
          </div>
          <div className="form-group">
            <input
              ref={password}
              type="text"
              name="password"
              placeholder="mots de passe"
              id="password"
              required
            />
          </div>
          <input type="submit" value="Valider" />
        </form>
      </div>
    </div>
  );
}

export default Login;
