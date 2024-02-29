import { useEffect, useState } from "react";
import EmployeeService from "../Services/EmployeeService";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";

const MySwal = withReactContent(Swal);

const ListEmployee = () => {
    const navigate = useNavigate();
    const [employees, setEmployees] = useState([]);

    const editEmployee = (employeeId) => {
        navigate(`add-employee/${employeeId}`);
    };

    const deleteEmployee = (employeeId) => {
        MySwal.fire({
            title: "Você tem certeza?",
            text: "Isso irá apagar permanentemente o funcionário.",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#d33",
            cancelButtonColor: "#3085d6",
            confirmButtonText: "Sim, apagar!",
            cancelButtonText: "Cancelar",
        }).then((result) => {
            if (result.isConfirmed) {
                // If user clicks 'Yes', then delete the employee
                EmployeeService.DeleteEmployee(employeeId).then(() => {
                    setEmployees(employees.filter((emp) => emp.id !== employeeId));
                });
            }
        });
    };

    const viewEmployee = (employeeId) => {
        navigate(`view-employee/${employeeId}`);
    };

    useEffect(() => {
        EmployeeService.getEmployees()
            .then((res) => {
                setEmployees(res.data);
            })
            .catch((err) => console.log(err));
    }, []);

    return (
        <>
            <h2 className="text-center">Funcionários</h2>
            <div className="row">
                <table className="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Primeiro Nome do Funcionário</th>
                            <th>Segundo Nome do Funcionário</th>
                            <th>Email do Funcionário</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {employees.map((employee) => (
                            <tr key={employee.id}>
                                <td key={employee.firstName}>{employee.firstName}</td>
                                <td key={employee.lastName}>{employee.lastName}</td>
                                <td key={employee.email}>{employee.email}</td>
                                <td>
                                    <button className="btn btn-info" onClick={() => editEmployee(employee.id)}>
                                        Editar
                                    </button>
                                    <button className="btn btn-danger" onClick={() => deleteEmployee(employee.id)}>
                                        Detletar
                                    </button>
                                    <button className="btn btn-secondary" onClick={() => viewEmployee(employee.id)}>
                                        Visualizar
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </>
    );
};

export default ListEmployee;
