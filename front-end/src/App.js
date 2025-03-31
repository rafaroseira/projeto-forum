import Header from "./components/Header";
import Navbar from "./components/Navbar";
import Categories from "./components/Categories";
import Rules from "./components/Rules";
import Register from "./components/Register";
import Login from "./components/Login";
import TopicPreviews from "./components/TopicPreviews";
import Topic from "./components/Topic";
import {Route, Routes} from "react-router-dom";
import { AuthProvider } from "./components/Auth";
import CreateTopicForm from "./components/CreateTopicForm";
import {RequireAuth} from "./components/RequireAuth";
import MyTopics from "./components/MyTopics";

function App() {
  return (
    <div className="App">
      <AuthProvider>
      <Header/>
      <Navbar/>
      <Routes>
          <Route path="/" element={<Categories/>}></Route>
          <Route path="/regras" element={<Rules/>}></Route>
          <Route path="/entrar" element={<Login/>}></Route>
          <Route path="/registrar" element={<Register/>}></Route>
          <Route path="/topicos/categoria/:id" element={<TopicPreviews/>}></Route>
          <Route path="/topico/:id" element={<Topic/>}></Route>
          <Route path="/criar-topico" element={<RequireAuth><CreateTopicForm/></RequireAuth>}></Route>
          <Route path="/meus-topicos" element={<RequireAuth><MyTopics/></RequireAuth>}></Route>
      </Routes>
      </AuthProvider>
    </div>
  );
}

export default App;
