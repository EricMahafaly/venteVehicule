import axios from "axios";
import { useRef } from "react";

function AjoutAnnonce() {
  // state
  const description = useRef();
  const marque = useRef();
  const modele = useRef();
  const prix = useRef();

  // comportement
  const handleSubmit = (event) => {
    event.preventDefault();

    try {
      axios.post(
        "https://gifted-rice-production.up.railway.app/api/annonce",
        {}
      );
    } catch (error) {
      console.error("Erreur lors de la creation d' annonce");
    }
  };

  // affichage
  return (
    <div>
      <div className="header"></div>
      <div className="body">
        <h1>Ajouter une annonce</h1>
        <form onSubmit={handleSubmit}>
          <p>
            <label htmlFor="description">Description :</label>
            <br />
            <input ref={description} type="text" name="description" />
          </p>
          <p>
            <label htmlFor="marque">Marque :</label>
            <br />
            <input ref={marque} type="text" name="marque" />
          </p>
          <p>
            <label htmlFor="modele">Modele :</label>
            <br />
            <input ref={modele} type="text" name="modele" />
          </p>
          <p>
            <label htmlFor="prix">Prix :</label>
            <br />
            <input ref={prix} type="number" step="0.01" name="prix" />
          </p>
          <p>
            <input type="submit" value="Valider" />
          </p>
        </form>
      </div>
      <div className="footer"></div>
    </div>
  );
}

export default AjoutAnnonce;
