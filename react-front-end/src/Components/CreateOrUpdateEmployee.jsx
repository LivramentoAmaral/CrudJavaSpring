import { useEffect, useState } from "react";
import EmployeeService from "../Services/EmployeeService";
import { useNavigate, useParams } from "react-router-dom";

const CreateOrUpdateEmployee = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const [firstName,setFirstName] = useState("");
    const [lastName,setLastName] = useState("");
    const [email,setEmail] = useState("");
    const changeFirstNameHandler = (event) => {
        setFirstName(event.target.value)
    }
    const changeLastNameHandler = (event) => {
        setLastName(event.target.value);
    }
    const changeEmailHandler = (event) => {
        setEmail(event.target.value);
    }
    const saveOrUpdateEmployee = (e) => {
        e.preventDefault();
        const employee = {firstName: firstName,lastName:lastName,email:email};
        console.log(employee);
        if(id < 0){
            EmployeeService.createEmployee(employee).then(()=>{
                navigate("/");
            });
        } else {
            EmployeeService.updateEmployee(id,employee).then(()=>{
                navigate("/");
            });
        }
    }
    const cancel = () => {
        navigate("/")
    }
    useEffect(()=>{
        if(id < 0) {
            return;
        }
        EmployeeService.getEmployeeById(id).then(res => {
            const employee = res.data;
            setFirstName(employee.firstName);
            setLastName(employee.lastName);
            setEmail(employee.email);
        }).catch(err => console.log(err));
    },[]);
    
    return(
        <>
        <div className="container" style={{display:'flex', height:'100%', width:'100%', justifyContent:"center"}}>
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3" style={{display:'flex', height:'100%', width:'100%', justifyContent:"center"}}>
                    <h3 className="text-center">{id<0?"Adicionar":"Editar"} Usuário</h3>
                    <div className="card-body" style={{width:"100%"}}>
                        <form className="form" style={{width:"100%"}}>
                            <div className="form-group" >
                                <label>Nome</label>
                                <input type="text" placeholder="Nome" name="firstName" className="form -control" 
                                value={firstName} onChange={changeFirstNameHandler}/>
                            </div>
                            <div className="form-group">
                                <label>Segundo Nome</label>
                                <input type="text" placeholder="Segundo Nome" name="lastName" className="form -control" 
                                value={lastName} onChange={changeLastNameHandler}/>
                            </div>
                            <div className="form-group">
                                <label>Email</label>
                                <input type="text" placeholder="Email" name="email" className="form -control" 
                                value={email} onChange={changeEmailHandler}/>
                            </div>
                            <div>
                            <button className="btn btn-success"  onClick={saveOrUpdateEmployee}>Savar</button>
                            <button className="btn btn-danger"onClick={cancel}>Cancelar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </>
    );
}
export default CreateOrUpdateEmployee;