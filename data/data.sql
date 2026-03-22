INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('USER');


INSERT INTO restaurant (nom, adresse, ville, description) VALUES
('Le Gourmet', '12 Rue de Paris', 'Paris', 'Restaurant gastronomique français'),
('Pizza Bella', '45 Avenue des Champs', 'Lyon', 'Pizzeria traditionnelle italienne'),
('Sushi Zen', '8 Boulevard de la Mer', 'Marseille', 'Restaurant japonais spécialisé en sushi'),
('Chez Mertina', '123 Rue Centrale', 'Antananarivo', 'Cuisine locale et moderne'),
('La Table d’Or', '5 Place de la République', 'Bordeaux', 'Cuisine française raffinée'),
('Burger Factory', '22 Rue des Lilas', 'Lille', 'Burgers gourmets et frites maison'),
('Taco Fiesta', '17 Rue de Mexico', 'Montpellier', 'Spécialités mexicaines authentiques'),
('Café du Port', '3 Quai des Mers', 'Marseille', 'Café et fruits de mer frais'),
('Le Petit Délice', '10 Rue Saint-Honoré', 'Paris', 'Pâtisseries et desserts artisanaux'),
('Dragon d’Or', '88 Avenue de Pékin', 'Lyon', 'Cuisine chinoise traditionnelle');


INSERT INTO plat (nom, prix, restaurant_id) VALUES
('Boeuf Bourguignon', 18.50, 1),
('Pizza Margherita', 12.00, 2),
('Sushi Maki', 15.00, 3),
('Ravitoto', 10.50, 4),
('Foie Gras', 25.00, 5),
('Cheeseburger', 11.00, 6),
('Tacos au Poulet', 9.50, 7),
('Moules Frites', 16.00, 8),
('Macaron Chocolat', 6.50, 9),
('Canard laqué', 20.00, 10);