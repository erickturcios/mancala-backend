TRUNCATE TABLE `sessions`;
TRUNCATE TABLE `configurations`;
TRUNCATE TABLE `game_boards`;
TRUNCATE TABLE `game_boards_history`;

INSERT INTO `sessions` (`id`, `session_id`, `created_on`, `updated_on`) VALUES
(22, '4a525c64-859d-4c95-99dd-44e8140dc5a1', '2023-09-26 18:30:24', NULL)
;

INSERT INTO `configurations` (`id`, `game_session`, `number_of_stones`, `step_back_allowed`, `auto_rotate`, `alias1`, `alias2`, `created_on`, `updated_on`) VALUES
(24, '4a525c64-859d-4c95-99dd-44e8140dc5a1', 6, 0, 0, 'Player 1', 'Player 2', '2023-09-26 18:30:25', NULL)
;

INSERT INTO `game_boards` (`id`, `game_session`, `p1_01`, `p1_02`, `p1_03`, `p1_04`, `p1_05`, `p1_06`, `p1_total`, `p2_01`, `p2_02`, `p2_03`, `p2_04`, `p2_05`, `p2_06`, `p2_total`, `player_to_move_next`, `winner`, `created_on`, `updated_on`) VALUES
(1, '4a525c64-859d-4c95-99dd-44e8140dc5a1', 8, 0, 2, 1, 9, 9, 3, 9, 1, 3, 9, 8, 8, 2, 2, 0, '2023-09-26 19:04:52', '2023-09-27 23:02:39')
;

INSERT INTO game_boards_history (id, game_session, p1_01, p1_02, p1_03, p1_04, p1_05, p1_06, p1_total, p2_01, p2_02, p2_03, p2_04, p2_05, p2_06, p2_total, player_to_move_next, winner, created_on) VALUES
(10, '4a525c64-859d-4c95-99dd-44e8140dc5a1', 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0, 1, 0, '2023-09-27 23:02:31'),
(11, '4a525c64-859d-4c95-99dd-44e8140dc5a1', 6, 6, 0, 7, 7, 7, 1, 7, 7, 6, 6, 6, 6, 0, 2, 0, '2023-09-27 23:02:33'),
(12, '4a525c64-859d-4c95-99dd-44e8140dc5a1', 7, 7, 0, 7, 7, 7, 1, 7, 7, 0, 7, 7, 7, 1, 1, 0, '2023-09-27 23:02:34'),
(13, '4a525c64-859d-4c95-99dd-44e8140dc5a1', 7, 7, 0, 0, 8, 8, 2, 8, 8, 1, 8, 7, 7, 1, 2, 0, '2023-09-27 23:02:35'),
(14, '4a525c64-859d-4c95-99dd-44e8140dc5a1', 8, 8, 1, 0, 8, 8, 2, 8, 0, 2, 9, 8, 8, 2, 1, 0, '2023-09-27 23:02:36')
;

COMMIT;