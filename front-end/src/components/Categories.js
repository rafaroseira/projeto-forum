import React, { useEffect, useState } from "react";

const Categories = () => {
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    const fetchCategories = async () => {
      const response = await fetch("http://localhost:8080/categories");
      const data = await response.json();
      setCategories(data);
      
    };

    fetchCategories();
  }, []);

  const groupByCategory = (data) => {
    return data.reduce((acc, category) => {
      const { subject } = category;
      if (!acc[subject]) {
        acc[subject] = [];
      }
      acc[subject].push(category);
      return acc;
    }, {});
  };

  const groupedCategories = groupByCategory(categories);

  return (
    <div>
      {Object.entries(groupedCategories).map(([subject, categories]) => (
        <div key={subject} className="assunto">
          <h2>{subject}</h2>
          <ul>
            {categories.map((category) => (
              <li key={category.id}>
                <a href={`http://localhost:3000/topicos/categoria/${category.id}`}>
                  {category.name}
                </a>
                <p>
                    {category.description}
                </p>
              </li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
};

export default Categories;
