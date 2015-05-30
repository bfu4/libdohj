/*
 * Copyright 2015 J. Ross Nicoll
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.altcoinj.params;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author jrn
 */
public class AbstractDogecoinParamsTest {
    private static final AbstractDogecoinParams params = DogecoinMainNetParams.get();

    @Test
    public void shouldCalculateBitcoinLikeDifficulty() {
        int previousHeight = 239;
        long previousBlockTime = 1386475638; // Block 239
        long lastRetargetDifficulty = 0x1e0ffff0;
        long lastRetargetTime = 1386474927; // Block 1
        long newDifficulty = params.getNewDifficultyTarget(previousHeight, previousBlockTime, lastRetargetDifficulty, lastRetargetTime);
        assertEquals(newDifficulty, 0x1e00ffff);

        previousHeight = 479;
        previousBlockTime = 1386475840;
        lastRetargetDifficulty = 0x1e0fffff;
        lastRetargetTime = 1386475638; // Block 239
        newDifficulty = params.getNewDifficultyTarget(previousHeight, previousBlockTime, lastRetargetDifficulty, lastRetargetTime);
        assertEquals(newDifficulty, 0x1e00ffff);

        previousHeight = 9599;
        previousBlockTime = 1386954113;
        lastRetargetDifficulty = 0x1c1a1206;
        lastRetargetTime = 1386942008; // Block 9359
        newDifficulty = params.getNewDifficultyTarget(previousHeight, previousBlockTime, lastRetargetDifficulty, lastRetargetTime);
        assertEquals(newDifficulty, 0x1c15ea59);
    }

    @Test
    public void shouldCalculateDigishieldDifficulty() {
        int previousHeight = 145000;
        long previousBlockTime = 1395094679;
        long lastRetargetDifficulty = 0x1b499dfd;
        long lastRetargetTime = 1395094427;
        long newDifficulty = params.getNewDifficultyTarget(previousHeight, previousBlockTime, lastRetargetDifficulty, lastRetargetTime);
        assertEquals(newDifficulty, 0x1b671062);
    }

    @Test
    public void shouldCalculateDigishieldDifficultyRounding() {
        // Test case for correct rounding of modulated time
        int previousHeight = 145001;
        long previousBlockTime = 1395094727;
        long lastRetargetDifficulty = 0x1b671062;
        long lastRetargetTime = 1395094679;
        long newDifficulty = params.getNewDifficultyTarget(previousHeight, previousBlockTime, lastRetargetDifficulty, lastRetargetTime);
        assertEquals(newDifficulty, 0x1b6558a4);
    }
}
