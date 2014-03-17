-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 12, 2014 at 04:06 PM
-- Server version: 5.5.35
-- PHP Version: 5.4.4-14+deb7u8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `recipe_app`
--

-- --------------------------------------------------------

--
-- Table structure for table `Ingredients`
--

CREATE TABLE IF NOT EXISTS `Ingredients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) NOT NULL,
  `IngredientCategory` set('Meat','Vegetables','Fruits','Dairy','Grains','Seafood','Spices','Dry Ingredients','Wet Ingredients','Canned Ingredients','Other') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Name` (`Name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=61 ;

--
-- Dumping data for table `Ingredients`
--

INSERT INTO `Ingredients` (`_id`, `Name`, `IngredientCategory`) VALUES
(1, 'cornstarch', 'Wet Ingredients'),
(2, 'water', 'Other'),
(3, 'garlic powder', 'Spices'),
(4, 'ground round steak', 'Meat'),
(5, 'vegetable oil', 'Wet Ingredients'),
(6, 'broccoli flourets', 'Vegetables'),
(7, 'onion', 'Vegetables'),
(8, 'reduced sodium soy sauce', 'Wet Ingredients'),
(9, 'brown sugar', 'Dry Ingredients'),
(10, 'ground ginger', 'Spices'),
(11, 'cooked rice', 'Grains'),
(12, 'chicken stove top stuffing mix', 'Other'),
(13, 'milk', 'Dairy'),
(14, 'ham', 'Meat'),
(15, 'eggs', 'Dairy'),
(16, 'cheddar cheese', 'Dairy'),
(17, 'salt', 'Dry Ingredients'),
(18, 'boneless chicken breasts', 'Meat'),
(19, 'unsweetened canned pineapple c', 'Fruits'),
(20, 'soy sauce', 'Wet Ingredients'),
(25, 'dry mustard', 'Spices'),
(26, 'fresh ground pepper', 'Vegetables'),
(27, 'green pepper', 'Vegetables'),
(28, 'mushrooms', 'Vegetables'),
(29, 'cherry tomatoes', 'Vegetables'),
(31, 'roma tomatoes', 'Vegetables'),
(32, 'garlic cloves', 'Spices'),
(33, 'olive oil', 'Wet Ingredients'),
(34, 'balsamic vinegar ', 'Wet Ingredients'),
(35, 'basil', 'Spices'),
(37, 'fresh cracker pepper', 'Spices'),
(38, 'italian bread', 'Grains'),
(39, 'parmigiano-reggiano cheese', 'Dairy'),
(52, 'meatballs', 'Meat'),
(54, 'spaghetti sauce', 'Canned Ingredients'),
(55, 'cream cheese', 'Dairy'),
(56, 'mayonnaise ', 'Other'),
(57, 'black pepper', 'Spices'),
(58, 'italian seasoning', 'Spices'),
(60, 'mozzarella cheese', 'Dairy');

-- --------------------------------------------------------

--
-- Table structure for table `Recipes`
--

CREATE TABLE IF NOT EXISTS `Recipes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) NOT NULL,
  `MealType` enum('Breakfast','Lunch','Dinner','Dessert','Snack/Appetizer') NOT NULL,
  `Ingredients` text NOT NULL,
  `IngredientsWithNumVal` text NOT NULL,
  `TimeDisplay` enum('<5min','5-10min','10-20min','20-30min','30+min') NOT NULL,
  `TimeActual` int(11) NOT NULL,
  `InstructionsText` text NOT NULL,
  `Calories` int(11) NOT NULL,
  `Rating` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `Recipes`
--

INSERT INTO `Recipes` (`id`, `Name`, `MealType`, `Ingredients`, `IngredientsWithNumVal`, `TimeDisplay`, `TimeActual`, `InstructionsText`, `Calories`, `Rating`) VALUES
(1, 'Beef and Broccoli Stir Fry', 'Dinner', 'cornstarch,water,garlic powder,ground round steak,vegetable oil,broccoli flourets,onion,reduced sodium soy sauce,brown sugar,ground ginger,cooked rice', '', '20-30min', 25, '1. In a bowl, combine 2 tablespoons cornstarch, 2 tablespoons water and garlic powder until smooth.\r\n2. Add 1 lbs beef (cut into strips) and toss.\r\n3. In a large skillet or wok over medium high heat, stir-fry beef in 1 tablespoon oil until beef reaches desired doneness; remove and keep warm.\r\n4. Stir-fry 4 cups broccoli and 1 sliced small onion in remaining oil for 4-5 minutes.\r\n5. Return beef to pan.\r\n6. Combine 1/3 cup soy sauce, 2 Tablespoons brown sugar, 1 teaspoon ginger, 1 Tablespoon cornstarch and 2 cups water until smooth; add to the pan.\r\n7. Cook and stir for 2 minutes.\r\n8. Serve over rice.\r\n', 150, 5),
(2, 'Breakfast Casserole', 'Breakfast', 'chicken stove top stuffing mix,milk,ham,eggs,cheddar cheese,salt', '', '30+min', 65, '1. Preheat oven to 350 degrees Fahrenheit.\r\n2. Lightly grease a 9 x 12 baking dish.\r\n3. Mix all the ingredients together (2 cups stuffing mix, 3/2 cup cubed ham, 6 eggs, 1 cup shredded cheese, and a 1/2 teaspoon salt).\r\n4. Pour into prepared baking dish.\r\n5. Bake for about 45 to 50 minutes or until set.', 200, 5),
(3, 'Hawaiian Chicken Kebabs', 'Dinner', 'boneless chicken breasts,unsweetened canned pineapple chunks,soy sauce,vegetable oil,brown sugar,garlic powder,ground ginger,dry mustard,fresh ground pepper,green pepper,mushrooms,cherry tomatoes,cooked rice', '', '30+min', 32, '1. Put 3/2 lbs chicken in large shallow dish.\r\n2. Drain 1 15.25 oz can of pineapple, keep 1/2 cup juice.\r\n3. Set pineapple aside.\r\n4. Mix juice with the next 7 ingredients(1/2 cup soy sauce, 1/4 cup vegetable oil, 1 tablespoon brown sugar, 1 teaspoon garlic powder, 2 teaspoons ground ginger, 1 teaspoon dry mustard, 1/4 teaspoon fresh ground pepper, ) in small pan.\r\n5. Bring to a boil.\r\n6. Reduce heat and simmer for 5 minutes.\r\n7. Pour over chicken.\r\n8. Cover and chill for 1 hour.\r\n9. Remove chicken from marinade, reserve marinade.\r\n10. Alternate chicken, pineapple, 1 in pieces of green pepper, 12 medium mushrooms, and 18 tomatoes on skewers.\r\n11. Grill kabobs over hot coals 20 minutes or until chicken is done.\r\n12. Turn and baste frequently with marinade.\r\n13. Serve over hot rice.', 370, 5),
(4, '', 'Breakfast', 'Test Ingredient,asdfasdf', '', '<5min', 0, '', 0, 0),
(5, 'Tomato and Basil Bruschetta', 'Snack/Appetizer', 'roma tomatoes,garlic cloves,olive oil,balsamic vinegar ,basil,salt,fresh cracker pepper,Italian Bread,parmigiano-reggiano cheese', '', '30+min', 45, '1. Whisk together 2 cloves chopped garlic, 2 1/4 teaspoons vinegar, 1/2 teaspoon salt, 1/4 teaspoon pepper, and 2 tablespoons chopped basil. 2. When combined slowly drizzle in 3 tablespoons olive oil. 3. Add 6 diced tomatoes and let sit for 20 minutes at room temp. 4. Toast the 1 in slices of bread. This can be done either in the toaster or under the broiler. 6. When the bread is toasted rub each piece, on one side, with the whole garlic pieces. 7. Place the bread on a cookie sheet and top with tomato mixture. 8. Sprinkle on a 2 tablespoons cheese and broil till the cheese melts. 9. Serve immediately.', 235, 5),
(6, 'Test2', 'Breakfast', '', '', '<5min', 0, 'aogihag''asugala''akgak''agaga', 0, 0),
(7, 'Meatball Sandwiches', 'Lunch', 'meatballs,italian bread,spaghetti sauce,cream cheese,mayonnaise ,black pepper,italian seasoning,garlic powder,mozzarella cheese', '', '30+min', 45, '1. In a large pot combine 2 lbs meatballs and one jar of spaghetti sauce.\r\n2. Heat over medium until meatballs are heated throughout.\r\n3. Meanwhile, mix together one 8 oz package of cream cheese, 1/2 cup mayonnaise, 1 Tablespoon Italian seasoning, 1/4 teaspoon pepper, and a dash of garlic powder and set aside.\r\n4. Slice Italian style bread loaves horizontally with serrated bread knife.\r\n5. Cut loaves into three- or four-inch sections to make small sandwiches.\r\n6. Spread cream cheese mixture on insides of each sandwiche''s tops and bottoms.\r\n7. Preheat oven to 350°F.\r\n8. When meatballs are heated, spoon onto sandwiches and top with shredded mozzarella cheese.\r\n9. Put sandwich tops on and place on a foil-lined baking sheet.\r\n10. Bake about 10-15 minutes or until cheese is melted.\r\n', 1955, 3);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
http://3.4.11.1/
3.4.11.1