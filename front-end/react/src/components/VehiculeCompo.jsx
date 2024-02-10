function VehiculeCompo(props) {
  return (
    <div className={props.container}>
      <img src={props.source} alt="img" />
      {props.marque}
      <br />
      {props.prix}
    </div>
  );
}

export default VehiculeCompo;
