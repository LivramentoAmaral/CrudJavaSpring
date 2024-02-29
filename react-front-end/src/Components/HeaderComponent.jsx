import { Link } from "react-router-dom";
const HeaderComponent = () => {
    return(
        <>
        <header className="header bg-dark"> 
            <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                <div><Link to="/" className="navbar-brand">Gerenciamento de Funcionários</Link></div>
                <div><Link to="/add-employee/-1" className="navbar">Adicinar Funcionário</Link></div>
            </nav>
        </header>
        </>
    );
}
export default HeaderComponent;