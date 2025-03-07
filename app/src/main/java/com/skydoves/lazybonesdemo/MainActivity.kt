/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("unused")

package com.skydoves.lazybonesdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.balloon.Balloon
import com.skydoves.lazybones.lifecycleAware
import com.skydoves.lazybonesdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private val binding: ActivityMainBinding by lifecycleAware {
    ActivityMainBinding.inflate(layoutInflater)
  }.lazy()

  private val mainViewModel = MainViewModel(this)

  private val balloon: Balloon by lifecycleAware { BalloonUtils.getProfileBalloon(baseContext) }
    .onCreate { showAlignTop(binding.button) }
    .onDestroy { dismiss() }
    .lazy()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
  }
}
