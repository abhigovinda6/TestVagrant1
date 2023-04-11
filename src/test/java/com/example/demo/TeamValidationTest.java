package com.example.demo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TeamValidationTest {

    @Test
    public void validateTeamDetails() throws Exception {

        // Read the contents of the JSON file
        String jsonFilePath = System.getProperty("user.dir") + "\\assignment.txt";
        byte[] jsonData = Files.readAllBytes(Paths.get(jsonFilePath));

        // Create a JSONObject from the JSON file contents
        JSONObject teamJson = new JSONObject(new String(jsonData));

        // Retrieve the player array from the JSONObject
        JSONArray playersJson = teamJson.getJSONArray("player");

        int numForeignPlayers = 0;
        int numWicketKeepers = 0;

        // Iterate over each player and count the number of foreign players and wicketkeepers
        for (int i = 0; i < playersJson.length(); i++) {
            JSONObject player = playersJson.getJSONObject(i);
            if (!player.getString("country").equals("India")) {
                numForeignPlayers++;
            }
            if (player.getString("role").equals("Wicket-keeper")) {
                numWicketKeepers++;
            }
        }

        // Assert the conditions:
        // There should be 4 foreign players and atleast one wicket keeper
        Assert.assertEquals(numForeignPlayers, 4);
        Assert.assertTrue(numWicketKeepers >= 1);
    }
}
