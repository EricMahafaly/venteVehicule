import axios from "axios";
import { useEffect, useState } from "react";
import "../styles/home.css";
import VehiculeCompo from "../components/VehiculeCompo";
import image from "../Blem.PNG";

function Home() {
  // state
  const [data, setData] = useState([]);
  const [dataImg, setDataImg] = useState([]);

  useEffect(() => {
    axios
      .get("https://gifted-rice-production.up.railway.app/api/annonce")
      .then((response) => setData(response.data))
      .catch((error) =>
        console.error("Erreur lors de la recuperation des donnees : ", error)
      );

    axios
      .get(`https://gifted-rice-production.up.railway.app/api/annonce/image`)
      .then((response) => setDataImg(response.data))
      .catch((error) =>
        console.error("Erreur lors de la recuperation de donnee : ", error)
      );
  }, []);

  for (let i = 0; i < dataImg.length; i++) {
    console.log(dataImg[0].url);
  }

  // comportement

  // affichage
  return (
    <div>
      <div className="header"></div>
      <div className="body">
        <ul>
          {data.map((vehicule) => (
            <li key={vehicule.idAnnonce}>
              <VehiculeCompo
                container="vehicule"
                source={image}
                marque={vehicule.marque}
                prix={vehicule.prix}
              />
            </li>
          ))}
        </ul>
      </div>
      <div className="footer"></div>
    </div>
  );
}

export default Home;
