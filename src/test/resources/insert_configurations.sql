TRUNCATE TABLE `configurations`;

INSERT INTO `configurations` (`id`, `game_session`, `number_of_stones`, `step_back_allowed`, `auto_rotate`, `alias1`, `alias2`, `created_on`, `updated_on`) VALUES
(21, '78fd56a1-aa24-471d-a2f3-51ecaf8c59ae', 8, 0, 1, 'Player 1 - 5', 'Player 2', '2023-09-26 02:59:22', '2023-09-26 03:15:15'),
(22, '98e7b2e8-7839-42aa-b863-92d6318c33dc', 6, 0, 0, 'Player 1', 'Player 2', '2023-09-26 03:17:13', '2023-09-26 03:18:34'),
(23, 'bcee66dd-a317-49a1-9546-f6525ca8e118', 3, 1, 0, 'Erick', 'Otro', '2023-09-26 03:19:06', '2023-09-27 10:34:07'),
(24, 'c34565ce-dd43-41f0-bd74-45f3ba5dc507', 6, 0, 0, 'Player 1', 'Player 2', '2023-09-26 18:30:25', NULL);

COMMIT;