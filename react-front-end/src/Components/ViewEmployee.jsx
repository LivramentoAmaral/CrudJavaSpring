import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom"
import EmployeeService from "../Services/EmployeeService";

const ViewEmployee = () => {
    const {id} = useParams();
    const navigate = useNavigate();
    const [employee, setEmployee] = useState({});
    useEffect(() => {
        EmployeeService.getEmployeeById(id).then( (res) =>{
            setEmployee(res.data);
            console.log(employee)
        }).catch(err => console.log(err));
    },[]);
    return(
    <>
      <div class="card" style={{"width": "18rem","marginTop":"4rem","marginLeft":"auto","marginRight":"auto"}}>
        <img class="card-img-top" style={{"border":"2px solid"}} src="https://www.svgrepo.com/show/33058/employee.svg" alt="Card image cap"/>
        <div class="card-body">
        <h5 class="card-title">Detalhes do funcionário {employee.firstName} {employee.lastName}</h5>
        <ul class="list-group list-group-flush">        <li class="list-group-item">Email: {employee.email}</li>
        </ul>
        <button onClick={()=>{navigate('/')}} class="btn btn-primary">Voltar</button>
        </div>
     </div>
    </>
    );
}
export default ViewEmployee;