/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.animaldoppelganger

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_doppelganger.*
import java.io.IOException

class DoppelgangerFragment : Fragment() {

  companion object {
    const val ARG_POSITION = "position"

    fun getInstance(position: Int): Fragment {
      val doppelgangerFragment = DoppelgangerFragment()
      val bundle = Bundle()
      bundle.putInt(ARG_POSITION, position)
      doppelgangerFragment.arguments = bundle
      return doppelgangerFragment
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_doppelganger, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    val position = requireArguments().getInt(ARG_POSITION)
    val imageFilePath = getString(R.string.doppelganger_image_path, position)
    val doppelgangerNamesArray = requireContext().resources.getStringArray(R.array.doppelganger_names)

    setImageFromAssetsFile(requireContext(), imageFilePath)
    doppelgangerNameTv.text = doppelgangerNamesArray[position]
  }

  /**
   * Gets the file from assets, converts it into a bitmap and sets it on the ImageView
   * @param context a Context instance
   * @param filePath relative path of the file
   */
  private fun setImageFromAssetsFile(context: Context, filePath: String) {
    val imageBitmap: Bitmap?
    val assets = context.resources.assets
    try {
      val fileStream = assets.open(filePath)
      imageBitmap = BitmapFactory.decodeStream(fileStream)
      fileStream.close()
      doppelgangerIv.setImageBitmap(imageBitmap)
    } catch (e: IOException) {
      e.printStackTrace()
      Toast.makeText(context, getString(R.string.image_loading_error), Toast.LENGTH_SHORT).show()
    }
  }
}
